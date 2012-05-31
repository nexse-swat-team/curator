define([
    'jQuery',
    'Underscore',
    'Backbone',
    'text!templates/newsletter.html'
], function ($, _, Backbone, nlTpl) {
    return Backbone.View.extend({
        //el:("html"),

        render:function () {
            var nw =window.open();
            nw.document.write(nlTpl);
            //this.$el.html(nlTpl);
/*
            _.each(this.model.models, function (rowModel) {
                new RowView({model:rowModel.toJSON()}).render();
            }, this);
*/

        }


    });
});