define([
    'jQuery',
    'Underscore',
    'Backbone',
    'views/daCanaleListRow',
    'text!templates/daCanaleList.html'
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
            "click #vai_lavorazione":"clickLavorazione"
        },

        clickLavorazione:function () {
            window.app_router.navigate("lavorazione", {trigger:true})
        }


    });
});