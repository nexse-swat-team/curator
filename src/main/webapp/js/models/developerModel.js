
prjmgmt.DeveloperModel = Backbone.Model.extend({

    /*urlRoot:"../api/projects",*/

    initialize:function () {
        //this.projectList = new prjmgmt.ProjectModelCollection();
        //this.reports.url = '../api/employees/' + this.id + '/reports';
    }

});

prjmgmt.DeveloperModelCollection = Backbone.Collection.extend({

    model:prjmgmt.DeveloperModel,

    url:"api/developers.js"

/*
    findByName:function (key) {
        // TODO: Modify service to include firstName in search
        var url = (key == '') ? '../api/employees' : "../api/employees/search/" + key;
        console.log('findByName: ' + key);
        var self = this;
        $.ajax({
            url:url,
            dataType:"json",
            success:function (data) {
                console.log("search success: " + data.length);
                self.reset(data);
            }
        });
    }
*/

});