define([
    'jQuery',
    'Underscore',
    'Backbone',
    'text!templates/imgScroller.html'
], function ($, _, Backbone, tplScroller) {
    return Backbone.View.extend({
        initialize: function(){
          this.setElement($("#normalizzato_list_row_"+this.model.id+" #images"));
        },
        render:function () {
            var tpl = _.template(tplScroller);
            this.$el.append(tpl(this.model));
            $('#slider1').tinycarousel();

            //this._bindEvents();
        },
        _bindEvents:function () {
            var id = this.model.id,
                self=this;
            $("#"+id+"_elimina").click(function(){
                $("#dacanale_list_row_"+id).slideUp();
            });
            $("#"+id+"_aggiungi").toggle(function(){
                daLavorareCollection.add(self.model);
            },function(){
                daLavorareCollection.remove(self.model);
            })
        }
    });
});