define([
    'jQuery',
    'Underscore',
    'Backbone',
    'views/enrichedDataListRow',
    'text!templates/enrichedDataList.html'
], function ($, _, Backbone, RowView, mainTpl) {
    return Backbone.View.extend({
        el:("#page"),

        render:function () {
            this.$el.html(mainTpl);
            _.each(this.model.models, function (rowModel) {
                new RowView({model:rowModel}).render();
            }, this);

        },

        events:{
            "click #create_newsletter":"clickCreateNewsletter"
        },

        clickCreateNewsletter:function () {
            window.app_router.navigate("createNewsletter", {trigger:true})
        }



    });
});