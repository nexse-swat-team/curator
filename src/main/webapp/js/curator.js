var curator = {
    tweets:{},
    selectedTweetIds:[],

    add:function (tweetId) {
        curator.selectedTweetIds.push(tweetId);
        $('#tweet' + tweetId).slideUp();
        this.refreshNewsletter();
    },
    defaultSubject:function () {
        return curatorConfig.mailSubjectPrefix + curatorConfig.newsletterName + ' (' + this.today() + ')';
    },
    handleTweets:function (tweets) {
        $.each(tweets, function (index, tweet) {
            $('#tweets').append(curator.renderForTimeline(tweet));
            curator.tweets[tweet.id] = tweet;
        });
    },
    ignore:function (tweetId) {
        $('#tweet' + tweetId).slideUp();
    },
    refreshNewsletter:function () {
        html = this.renderNewsletter();
        $('#newsletter').html(html);
        $('#twitterForm').fadeOut(600, function () {
            $('#newsletterForm').fadeIn(600, function () {
                $('#sendHelp').fadeOut(20000);
            });
        });
    },
    refreshTweets:function () {
        var twitterScreenName = $('#twitterScreenName')[0].value;
        var limit = $('#twitterLimit')[0].value;
        $('#tweets').html('&nbsp;'); // cleanup
        twitterlib.timeline(twitterScreenName, {limit:limit, rts:true}, this.handleTweets);
    },
    renderForNewsletter:function (tweet) {
        var html = '<li><article><div class="tweet" id="selectedTweet' + tweet.id + '">';
        html += '<div class="vcard"><a href="http://twitter.com/' + tweet.user.screen_name + '" class="url"><img style="height: 48px; width: 48px;" alt="' + tweet.user.name + '" class="photo fn" height="48" src="' + tweet.user.profile_image_url + '" width="48" /></a></div>';
        html += '<div class="hentry"><strong><a href="http://twitter.com/';
        html += tweet.user.screen_name + '" ';
        html += 'title="' + tweet.user.name + '">' + tweet.user.screen_name + '</a></strong> ';
        html += '<span class="entry-content">';
        html += twitterlib.ify.clean(twitterlib.expandLinks(tweet));
        html += '</span> <span class="meta entry-meta">';
        html += '<a href="http://twitter.com/' + tweet.user.screen_name;
        html += '/status/' + tweet.id_str + '" class="entry-date" rel="bookmark"><span class="published" title="';
        html += twitterlib.time.datetime(tweet.created_at) + '">' + twitterlib.time.relative(tweet.created_at) + '</span></a>';
        if (tweet.source) html += ' <span>from ' + tweet.source + '</span>';
        if (tweet.retweetedby) html += ' <span>retweeted by ' + tweet.retweetedby.screen_name + '</span>';
        html += '</span></div></div></article></li>';

        return html;
    },
    renderNewsletter:function () {
        var html = '<section><h1>' + $('#newsletterName')[0].value + '</h1><ul>';
        $.each(curator.selectedTweetIds, function (index, tweetId) {
            html += curator.renderForNewsletter(curator.tweets[tweetId]);
        });
        html += '</ul></section>';
        return html;
    },
    renderForTimeline:function (tweet) {
        var html = '<li><div class="tweet" id="tweet' + tweet.id + '">';
        html += '<div class="vcard"><a href="http://twitter.com/' + tweet.user.screen_name + '" class="url"><img style="height: 48px; width: 48px;" alt="' + tweet.user.name + '" class="photo fn" height="48" src="' + tweet.user.profile_image_url + '" width="48" /></a></div>';
        html += '<div class="hentry"><strong><a href="http://twitter.com/';
        html += tweet.user.screen_name + '" ';
        html += 'title="' + tweet.user.name + '">' + tweet.user.screen_name + '</a></strong> ';
        html += '<span class="entry-content">';
        html += twitterlib.ify.clean(twitterlib.expandLinks(tweet));
        html += '</span> <span class="meta entry-meta">';
        html += '<button class="btn btn-mini" onclick="curator.add(' + tweet.id + ');"><i class="icon-ok-sign"></i> add</button> ';
        html += '<button class="btn btn-mini" onclick="curator.ignore(' + tweet.id + ');"><i class="icon-remove-sign"></i> ignore</button> ';
        html += '<a href="http://twitter.com/' + tweet.user.screen_name;
        html += '/status/' + tweet.id_str + '" class="entry-date" rel="bookmark"><span class="published" title="';
        html += twitterlib.time.datetime(tweet.created_at) + '">' + twitterlib.time.relative(tweet.created_at) + '</span></a>';
        if (tweet.source) html += ' <span>from ' + tweet.source + '</span>';
        if (tweet.retweetedby) html += ' <span>retweeted by ' + tweet.retweetedby.screen_name + '</span>';
        html += '</span></div></div></li>';

        return html;
    },
    send:function () {
        var request = {
            "receivers":$('#mailTo')[0].value.split(','),
            "sender":$('#mailFrom')[0].value,
            "body":this.wrapNewsletterInHtmlForMail(),
            "subject":$('#mailSubject')[0].value
        };
        $.post('api/mail', JSON.stringify(request))
            .success(function () {
                $('#sendHelp').removeClass('label-info').addClass('label-success')
                    .html('The newsletter was sent successfully.').show();
            })
            .error(function () {
                $('#sendHelp').removeClass('label-info').addClass('label-important')
                    .html('There was an error sending the newsletter.').show();
            })
            .complete(function () {
                $('#newsletterSendModal').modal('hide')
            });
        return false;
    },
    today:function () {
        var now = new Date();
        return now.getFullYear() + '/' + (now.getMonth() + 1) + '/' + now.getDate();
    },
    wrapNewsletterInHtmlForMail:function () {
        var html = '<!DOCTYPE html><html lang="en"><head><meta charset="utf-8"><style type="text/css">';
        html += '/* tweet styles */\
#tweets { margin: 0; padding: 0; }\
#tweets p { margin: 14px;}\
#tweets a:hover, #tweets a:active, #tweets a:focus, #tweets a { text-decoration: none; }\
#tweets a:hover { text-decoration: underline; }\
#tweets a img { border: 0; }\
#tweets li { padding: 0; margin: 0; list-style: none; }\
.tweet { clear: left; min-height: 48px; padding: 9px; line-height: 1.1; position: relative; }\
#tweets li.searchterm { font-size: 22px; padding: 20px 0; }\
.vcard { margin-top: 5px; float: left;}\
.hentry { margin-left: 58px; }\
.hentry .meta { font-size: 0.764em; margin: 3px 0 0; display: block; }\
#tweets a { color: #00f; }\
.hentry .meta, #tweets .meta a { color: #999; }\
body { background-color: #F5F5F5; font-family: "Helvetica Neue", Helvetica, Arial, sans-serif; font-size: 13px; line-height: 18px;}\
h1 { padding: 10px; }\
li { padding: 0; margin: 0; list-style: none;}';
        html += '</style></head><body>';
        html += this.renderNewsletter();
        html += '</body></html>';
        return html;
    }
};

if (curatorConfig.debug) {
    twitterlib.debug({
        timeline:'test-data/timeline%page%.json?callback=callback'
    });
}

$('#newsletterName')[0].value = curatorConfig.newsletterName;
$('#twitterScreenName')[0].value = curatorConfig.twitterScreenName;
$('#mailFrom')[0].value = curatorConfig.mailFrom;
$('#mailSubject')[0].value = curator.defaultSubject();

$(document).ready(function () {
    curator.refreshTweets();
});

$('#twitterRefreshButton').click(function (event) {
    event.preventDefault();
    curator.refreshTweets();
});

$.ajaxSetup({
    dataType:'json',
    headers:{'Content-Type':'application/json;charset=UTF-8'},
    type:'POST'
});