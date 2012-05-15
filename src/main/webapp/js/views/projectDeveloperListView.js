prjmgmt.ProjectDeveloperListView = Backbone.View.extend({
    initialize:function () {
        debugger;
        var currElem = $("#" + this.id);
        if (currElem.length != 0) {
            currElem.remove();
        }
        this.setElement($("<tr id='" + this.id + "' style='display: none;'></tr>"));

    },

    render:function (prjid) {
        var tableTpl = _.template(prjmgmt.tpl.get("projectDeveloperTable")),
            tableTplEl = $(tableTpl(
                {
                    "prjid":prjid
                }
            ));


        _.each(this.model.models, function (developer) {
            var rowTpl = _.template(prjmgmt.tpl.get("projectDeveloperTableRow")),
                tableBody = tableTplEl.find("#dev-proj-" + prjid + " tbody");
            tableBody.append(rowTpl(developer.toJSON()))
        }, this);


        $(this.el).append(tableTplEl);

        $("#prj-" + prjid + "-row").after(this.el);
        $(this.el).show();
        tableTplEl.find("#dev-proj-" + prjid).slideToggle("slow");
        return this;
    }


});

prjmgmt.ProjectDeveloperListView.close = function (prjid) {
    var rowId = "#prj-" + prjid + "-developers";
    debugger;
    $("#dev-proj-" + prjid).slideToggle("slow", function () {
        $(rowId).hide();
    });
}