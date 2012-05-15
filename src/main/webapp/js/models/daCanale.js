define([
    'jQuery',
    'Underscore',
    'Backbone'
], function ($, _, Backbone) {
    var _DaCanale, _DaCanaleCollection;

    _DaCanale = Backbone.Model.extend({
    });

    _DaCanaleCollection = Backbone.Collection.extend({
        model:_DaCanale,

        url:"api/daCanale.js"

    });

    return {
        DaCanale:_DaCanale,
        DaCanaleCollection:_DaCanaleCollection
    };
})
;