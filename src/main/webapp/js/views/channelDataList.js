define([
    'jQuery',
    'Underscore',
    'Backbone',
    'views/channelDataListRow',
    'text!templates/channelDataList.html'
], function ($, _, Backbone, RowView, mainTpl) {
    return Backbone.View.extend({
        el:("#page"),

        render:function () {
            this.$el.html(mainTpl);
            _.each(this.model.models, function (rowModel) {
                new RowView({model:rowModel.toJSON()}).render();
            }, this);

        },

        events:{
            "click #enrich":"enrichClicked"
        },

        enrichClicked:function () {
            window.app_router.navigate("enrich", {trigger:true})
        }


    });
});