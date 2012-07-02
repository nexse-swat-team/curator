define([
    'jQuery',
    'Underscore',
    'Backbone',
    'text!templates/enrichedDataListRow.html',
    'models/enrichedData'
], function ($, _, Backbone, rowTpl, enrichedDataModule) {
    var x = {};

    x.Selector = {};
    x.Selector.getSelected = function () {
        var t = '';
        if (window.getSelection) {
            t = window.getSelection();
        } else if (document.getSelection) {
            t = document.getSelection();
        } else if (document.selection) {
            t = document.selection.createRange().text;
        }
        return t;
    }

    return Backbone.View.extend({
        el:("#enricheddata_list #body"),

        initialize:function(){
            this.undelegateEvents();
        },

        render:function () {
            var tpl = _.template(rowTpl);
            this.$el.append(tpl(this.model.toJSON()));
            //new ImgScroller({model:this.model}).render();
        },

        events:function () {
            var ret = {};
            ret["change #en-" + this.model.id + "-title"] = "titlechanged";
            ret["change #en-" + this.model.id + "-abstract"] = "abstractchanged";
            ret["change #en-" + this.model.id + "-category"] = "categorychanged";
            ret["click #en-" + this.model.id + "-question"] = "showtext";
            ret["click #en-" + this.model.id + "-saveTxt"] = "savetext";
            ret["click #en-" + this.model.id + "-close"] = "closeEnriched";
            return ret;
        },

        titlechanged:function (event) {
            this.model.set("title", event.target.value, {silent:true})
        },
        abstractchanged:function (event) {
            this.model.set("abstractTxt", event.target.value, {silent:true})
        },
        categorychanged:function (event) {
            this.model.set("category", event.target.value, {silent:true})
        },
        showtext:function (event) {
            $("#en-" + this.model.id + "-article").modal("show");
        },
        savetext:function (event) {
            //var selectedTxt = getSelectedTxt("#en-"+this.model.id+"-article .modal-body");
            var selectedTxt = x.Selector.getSelected();
            var abstract = $("#en-" + this.model.id + "-abstract");
            abstract.val(selectedTxt);
            abstract.trigger('change');
            $("#en-" + this.model.id + "-article").modal("hide");

        },
        closeEnriched:function (event) {
            $("#enricheddata_list_row_" + this.model.id).slideUp();
            enrichedDataModule.enrichedDataCollection.remove(this.model);
        }

    });
});