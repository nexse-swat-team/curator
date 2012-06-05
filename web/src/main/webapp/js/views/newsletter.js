define([
    'jQuery',
    'Underscore',
    'Backbone',
    'text!templates/newsletter.html'
], function ($, _, Backbone, nlTpl) {
    return Backbone.View.extend({

        render:function () {
            var tmp={},
                tpl = _.template(nlTpl),
                nw = window.open();
            tmp["data"]=this.model;
            nw.document.write(tpl(tmp));
        }
    });
});