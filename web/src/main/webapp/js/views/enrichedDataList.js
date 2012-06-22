define([
    'jQuery',
    'Underscore',
    'Backbone',
    'views/enrichedDataListRow',
    'text!templates/enrichedDataList.html',
    'models/enrichedData',
    'views/newsletter',
    'models/newsletter'
], function ($, _, Backbone, RowView, mainTpl, enrichedDataModule, NewsletterView, newsletter) {
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
            "click #create_newsletter":"clickCreateNewsletter",
            "click #send_newsletter":"clickSendNewsletter"
        },

        clickCreateNewsletter:function (e) {
            //window.app_router.navigate("createNewsletter", {trigger:true})
            var newsletter=_.groupBy(enrichedDataModule.enrichedDataCollection.models, function(model){
                return model.get("category");
            });
            new NewsletterView({model:newsletter}).render();
        },
        clickSendNewsletter:function (e) {
            $.ajax({
                url: "services/rest/newsletter/",
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify({body:newsletter.data,token:newsletter.token}),
                dataType: 'json'
            })
        }

    });
});