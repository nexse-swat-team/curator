define([
    'jQuery',
    'Underscore',
    'Backbone'
], function ($, _, Backbone) {
    var _ChannelData, _ChannelDataCollection, channelDataCollection;

    _ChannelData = Backbone.Model.extend({
    });

    _ChannelDataCollection = Backbone.Collection.extend({
        model:_ChannelData,

        url:"services/rest/channeldata",
        parse:function(response){
            return response.data;
        }

    });



    return {
        ChannelData:_ChannelData,
        ChannelDataCollection:_ChannelDataCollection,
        channelDataCollection:new _ChannelDataCollection()
    };
})
;