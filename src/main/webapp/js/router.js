// Filename: router.js
define([
    'jQuery',
    'Underscore',
    'Backbone',
    'views/daCanaleList',
    'views/normalizzatoList',
    'models/daCanale',
    'models/daLavorare',
    'models/newsletter'
], function ($, _, Backbone, DaCanaleListView, NormalizzatoListView, daCanale, daLavorareModule, newsletter) {
    var AppRouter = Backbone.Router.extend({
        routes:{
            'lavorazione':'lavorazioneAction',
            'generaNewsletter':'generaNewsletterAction',
            // Default
            '*actions':'defaultAction'
        },
        defaultAction:function (actions) {
            var mainView, daCanaleCollection;
            // We have no matching route, lets display the home page
            daCanaleCollection = new daCanale.DaCanaleCollection();
            daCanaleCollection.fetch({
                    success:function () {
                        new DaCanaleListView({model:daCanaleCollection}).render();
                    }
                }

            )
        },
        lavorazioneAction:function(){
            var normalizzatoList = new NormalizzatoListView({model: daLavorareModule.daLavorareCollection});
            normalizzatoList.render();
        },
        generaNewsletterAction:function(){
            newsletter=_.groupBy(daLavorareModule.daLavorareCollection.models, function(model){
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