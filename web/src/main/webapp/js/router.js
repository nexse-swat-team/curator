// Filename: router.js
define([
    'jQuery',
    'Underscore',
    'Backbone',
    'views/channelDataList',
    'views/enrichedDataList',
    'views/newsletter',
    'models/channelData',
    'models/enrichedData',
    'models/newsletter'
], function ($, _, Backbone, ChannelDataListView, EnrichedDataListView, NewsletterView, channelDataModule, enrichedDataModule, newsletter) {

    var pag1, pag2;


    var AppRouter = Backbone.Router.extend({
            routes:{
                'enrich':'enrichAction',
                //"createNewsletter":'createNewsletterAction',
                // Default
                '*actions':'defaultAction'
            },
            defaultAction:function (actions) {
                if (pag1) {
                    pag1.undelegateEvents();
                }

                enrichedDataModule.enrichedDataCollection.reset();
                channelDataModule.channelDataCollection.fetch({
                        success:function () {
                            pag1 = new ChannelDataListView({model:channelDataModule.channelDataCollection});
                            pag1.render();
                        }
                    }

                )
            },
            enrichAction:function () {
                if (pag2) {
                    pag2.undelegateEvents();
                }
                pag2 = new EnrichedDataListView({model:enrichedDataModule.enrichedDataCollection});
                pag2.render();
            }
            /*
             createNewsletterAction:function(){
             newsletter=_.groupBy(enrichedDataModule.enrichedDataCollection.models, function(model){
             return model.get("category");
             });
             new NewsletterView({model:newsletter}).render();

             }
             */
        }),
        initialize = function () {
            Backbone.history.start();
            return function () {
            };
        };

    window.app_router = new AppRouter;

    return {
        initialize:initialize
    };
});