define(['order!libs/jquery/jquery-1.7.2', 'order!libs/tinycarousel/jquery.tinycarousel', 'order!libs/underscore/underscore', 'order!libs/backbone/backbone'],
    function(){
        return {
            Backbone: Backbone.noConflict(),
            _: _.noConflict(),
            $: jQuery.noConflict()
        };
    });