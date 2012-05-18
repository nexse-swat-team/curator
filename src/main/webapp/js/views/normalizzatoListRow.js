define([
    'jQuery',
    'Underscore',
    'Backbone',
    'text!templates/normalizzatoListRow.html',
    'models/newsletter',
    'views/imgScroller'
], function ($, _, Backbone, rowTpl, newsletter, ImgScroller) {
    return Backbone.View.extend({
        el:("#normalizzato_list #body"),
        render:function () {
            var tpl = _.template(rowTpl);
            this.$el.append(tpl(this.model.toJSON()));
            new ImgScroller({model:this.model}).render();
            //this._bindEvents();
        },
        _bindEvents:function () {
            var id = this.model.id,
                self = this;
            $("#" + id + "_elimina").click(function () {
                $("#dacanale_list_row_" + id).slideUp();
            });
            $("#" + id + "_aggiungi").toggle(function () {
                daLavorareCollection.add(self.model);
            }, function () {
                daLavorareCollection.remove(self.model);
            })
        }
    });
});