define([
    'jQuery',
    'Underscore',
    'Backbone',
    'text!templates/enrichedDataListRow.html',
    'models/newsletter',
    'views/imgScroller'
], function ($, _, Backbone, rowTpl, newsletter, ImgScroller) {
    return Backbone.View.extend({
        el:("#enricheddata_list #body"),
        render:function () {
            var tpl = _.template(rowTpl);
            this.$el.append(tpl(this.model.toJSON()));
            new ImgScroller({model:this.model}).render();
        }
    });
});