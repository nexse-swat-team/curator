define([
    'jQuery',
    'Underscore',
    'Backbone'
], function ($, _, Backbone) {
    var _EnrichedData = Backbone.Model.extend({
//        urlRoot:"api/enriched.js"
        url:"api/enriched.js"
    });


    return {
        enrichedDataCollection:new (Backbone.Collection.extend(
            {
                model:_EnrichedData

            }
        )),
        EnrichedData:_EnrichedData
    };

})
;