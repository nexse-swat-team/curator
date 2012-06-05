define([
    'jQuery',
    'Underscore',
    'Backbone'
], function ($, _, Backbone) {
    var _EnrichedData = Backbone.Model.extend({
        urlRoot:"services/rest/enricheddata/",
//        url:"api/enriched.js"
        defaults:{
            "category":"Technology"
        },

        parse:function(response){
            return response.data;
        }

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