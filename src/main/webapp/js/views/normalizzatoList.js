define([
    'jQuery',
    'Underscore',
    'Backbone',
    'views/normalizzatoListRow',
    'text!templates/normalizzatoList.html'
], function ($, _, Backbone, RowView, mainTpl) {
    return Backbone.View.extend({
        el:("#normalizzati"),

        render:function () {
            this.$el.html(mainTpl);
            _.each(this.model.models, function (rowModel) {
                new RowView({model:rowModel}).render();
            }, this);

        },

        events:{
            "click #vai_newsletter":"clickGeneraNewsletter"
        },

        clickGeneraNewsletter:function () {
            window.app_router.navigate("generaNewsletter", {trigger:true})
        }



    });
});