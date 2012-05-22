define([
    'jQuery',
    'Underscore',
    'Backbone',
    'text!templates/channelDataListRow.html',
    'models/enrichedData'
], function ($, _, Backbone, rowTpl, enrichedDataModule) {
    return Backbone.View.extend({
        el:("#channeldata_list #body"),
        render:function () {
            var tpl = _.template(rowTpl);
            this.$el.append(tpl(this.model));
            this._bindEvents();
        },

        _bindEvents:function () {
            var id = this.model.id,
                self = this,
                enrichedData;
            $("#" + id + "_delete").click(function () {
                $("#channeldata_list_row_" + id).slideUp();
            });
            $("#" + id + "_add").toggle(function () {

                enrichedData = new enrichedDataModule.EnrichedData({id:self.model.id});
                enrichedData.fetch({
                    success:function(model, response){
                        enrichedDataModule.enrichedDataCollection.add(new enrichedDataModule.EnrichedData(response.data));
                    }
                })
            }, function () {
                enrichedDataModule.enrichedDataCollection.remove(new enrichedDataModule.EnrichedData(self.model));
            })
        }
    });
});