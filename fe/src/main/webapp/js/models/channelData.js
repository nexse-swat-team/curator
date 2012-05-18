define([
    'jQuery',
    'Underscore',
    'Backbone'
], function ($, _, Backbone) {
    var _ChannelData, _ChannelDataCollection;

    _ChannelData = Backbone.Model.extend({
    });

    _ChannelDataCollection = Backbone.Collection.extend({
        model:_ChannelData,

        url:"api/channelData.js"

    });

    return {
        ChannelData:_ChannelData,
        ChannelDataCollection:_ChannelDataCollection
    };
})
;