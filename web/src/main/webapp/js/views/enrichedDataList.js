define([
    'jQuery',
    'Underscore',
    'Backbone',
    'views/enrichedDataListRow',
    'text!templates/enrichedDataList.html',
    'models/enrichedData',
    'views/newsletter'
], function ($, _, Backbone, RowView, mainTpl, enrichedDataModule, NewsletterView) {
    return Backbone.View.extend({
        el:("#page"),

        initialize:function(){
            this.$el.empty();
        },

        render:function () {
            this.$el.html(mainTpl);
            _.each(this.model.models, function (rowModel) {
                new RowView({model:rowModel}).render();
            }, this);

        },


        events:{
            "click #create_newsletter":"clickCreateNewsletter"
        },

        clickCreateNewsletter:function (e) {
            //window.app_router.navigate("createNewsletter", {trigger:true})
            var newsletter=_.groupBy(enrichedDataModule.enrichedDataCollection.models, function(model){
                return model.get("category");
            });
            new NewsletterView({model:newsletter}).render();
        }

    });
});