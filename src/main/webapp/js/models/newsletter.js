define([
    'jQuery',
    'Underscore',
    'Backbone'
], function ($, _, Backbone) {
    var _ArticoloNewsletter = Backbone.Model.extend({
    });

    return new (Backbone.Collection.extend({
        model:_ArticoloNewsletter

    }));

})
;