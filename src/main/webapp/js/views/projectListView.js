prjmgmt.ProjectListView = Backbone.View.extend({

    initialize:function () {
        //insert the table scaffold
        if ($("#prjmgmt-body #prjmgmt-project-table").length == 0) {
            $("#prjmgmt-body").children().remove();
            $("#prjmgmt-body").append(_.template(prjmgmt.tpl.get("projectTable")))
        }
        this.setElement($("#prjmgmt-project-table tbody")[0]);
        /*
         this.model.bind("reset", this.render, this);
         this.model.bind("add", function (employee) {
         $(self.el).append(new EmployeeListItemView({model:employee}).render().el);
         });
         */
    },

    render:function (eventName) {
        var tpl = _.template(prjmgmt.tpl.get("projectTableRow")),
            close;
        $(this.el).empty();


        _.each(this.model.models, function (project) {
            var body = $(this.el),
                newRow;
            newRow = $(tpl(project.toJSON()));
            newRow.toggle(function (event) {
                event.preventDefault();
                newRow.find("#prj-" + project.id + "-icon").removeClass("icon-chevron-down").addClass("icon-chevron-up");
                prjmgmt.app.navigate("developerForProject/" + project.id + "/0", {trigger:true, replace:true});
                debugger;
            }, function (event) {
                event.preventDefault();
                newRow.find("#prj-" + project.id + "-icon").removeClass("icon-chevron-up").addClass("icon-chevron-down");
                prjmgmt.app.navigate("developerForProject/" + project.id + "/1", {trigger:true, replace:true});
            });
            body.append(newRow);
        }, this);
        return this;
    }


});