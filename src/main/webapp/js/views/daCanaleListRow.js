define([
    'jQuery',
    'Underscore',
    'Backbone',
    'text!templates/daCanaleListRow.html',
    'models/daLavorare'
], function ($, _, Backbone, rowTpl, daLavorareModule) {
    return Backbone.View.extend({
        el:("#dacanale_list #body"),
        render:function () {
            var tpl = _.template(rowTpl);
            this.$el.append(tpl(this.model));
            this._bindEvents();
        },

        _bindEvents:function () {
            var id = this.model.id,
                self = this,
                daLavorare;
            $("#" + id + "_elimina").click(function () {
                $("#dacanale_list_row_" + id).slideUp();
            });
            $("#" + id + "_aggiungi").toggle(function () {

                daLavorare = new daLavorareModule.DaLavorare({id:self.model.id});
                daLavorare.fetch({
                    success:function(model, response){
                        daLavorareModule.daLavorareCollection.add(new daLavorareModule.DaLavorare(response));
                    }
                })
            }, function () {
                daLavorareModule.daLavorareCollection.remove(new daLavorareModule.DaLavorare(self.model));
            })
        }
    });
});