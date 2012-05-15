define([
    'jQuery',
    'Underscore',
    'Backbone'
], function ($, _, Backbone) {
    var _DaLavorare = Backbone.Model.extend({
//        urlRoot:"api/enriched.js"
        url:"api/enriched.js",
        defaults:{"img":"", "title":"", "abstract":""}

    });


    return {
        daLavorareCollection:new (Backbone.Collection.extend(
            {
                model:_DaLavorare

            }
        )),
        DaLavorare:_DaLavorare
    };

})
;