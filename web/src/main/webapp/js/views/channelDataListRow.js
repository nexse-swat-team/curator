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
                enrichedData,
                $elem;
            $("#" + id + "_delete").click(function () {
                $("#channeldata_list_row_" + id).slideUp();
            });
            $("#" + id + "_add").toggle(function () {

                $elem = $(this);
                $elem.children("i").toggleClass("icon-ok-sign",false);
                $elem.children("i").toggleClass("icon-remove-sign",true);
                $elem.html($elem.html().replace("add","remove"));
                enrichedData = new enrichedDataModule.EnrichedData({id:self.model.id});
                enrichedData.fetch({
                    success:function(model, response){
                        enrichedDataModule.enrichedDataCollection.add(new enrichedDataModule.EnrichedData(response.data));
                    }
                })
            }, function () {
                $elem.children("i").toggleClass("icon-remove-sign",false);
                $elem.children("i").toggleClass("icon-ok-sign",true);
                $elem.html($elem.html().replace("remove","add"));
                enrichedDataModule.enrichedDataCollection.remove(new enrichedDataModule.EnrichedData(self.model));
            })
        }
    });
});