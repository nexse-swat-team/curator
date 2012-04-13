var curator = {
    tweets:{},
    selectedTweetIds:[],

    add:function (tweetId) {
        curator.selectedTweetIds.push(tweetId);
        $('#tweet' + tweetId).slideUp();
        this.refreshNewsletter();
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
        var html = '<section><h1>' + $('#newsletterName')[0].value + '</h1><ul>';

        $.each(curator.selectedTweetIds, function (index, tweetId) {
            html += curator.renderForNewsletter(curator.tweets[tweetId]);
        });
        html += '</ul></section>';
        $('#newsletter').html(html);
        $('#twitterForm').fadeOut(600, function() {
            $('#newsletterForm').fadeIn(600, function() {
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
    }
};

if (curatorConfig.debug) {
    twitterlib.debug({
      timeline: 'test-data/timeline%page%.json?callback=callback'
    });
}

$('#newsletterName')[0].value = curatorConfig.newsletterName;
$('#twitterScreenName')[0].value = curatorConfig.twitterScreenName;
$('#mailFrom')[0].value = curatorConfig.mailFrom;
$('#mailSubject')[0].value = curatorConfig.mailSubjectPrefix + curatorConfig.newsletterName;

$(document).ready(function () {
    curator.refreshTweets();
});

$('#twitterRefreshButton').click(function (event) {
    event.preventDefault();
    curator.refreshTweets();
});
