// Filename: router.js
define([
    'jQuery',
    'Underscore',
    'Backbone',
    'views/channelDataList',
    'views/enrichedDataList',
    'models/channelData',
    'models/enrichedData',
    'models/newsletter'
], function ($, _, Backbone, ChannelDataListView, EnrichedDataListView, channelData, enrichedDataModule, newsletter) {
    var AppRouter = Backbone.Router.extend({
        routes:{
            'enrich':'enrichAction',
            "createNewsletter":'createNewsletterAction',
            // Default
            '*actions':'defaultAction'
        },
        defaultAction:function (actions) {
            var channelDataCollection = new channelData.ChannelDataCollection();
            channelDataCollection.fetch({
                    success:function () {
                        new ChannelDataListView({model:channelDataCollection}).render();
                    }
                }

            )
        },
        enrichAction:function(){
            var enrichedDataListView = new EnrichedDataListView({model: enrichedDataModule.enrichedDataCollection});
            enrichedDataListView.render();
        },
        createNewsletterAction:function(){
            newsletter=_.groupBy(enrichedDataModule.enrichedDataCollection.models, function(model){
                return model.get("category");
            });
        }
    }),
    initialize = function () {
        Backbone.history.start();
        return function(){};
    };

    window.app_router = new AppRouter;

    return {
        initialize:initialize
    };
});