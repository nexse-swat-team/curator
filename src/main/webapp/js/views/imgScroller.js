define([
    'jQuery',
    'Underscore',
    'Backbone',
    'text!templates/imgScroller.html'
], function ($, _, Backbone, tplScroller) {
    return Backbone.View.extend({
        initialize:function () {
            this.setElement($("#enricheddata_list_row_" + this.model.id + " #images"));
            this.model.bind("change:img", this._clickOnImg);
        },
        render:function () {
            var tpl = _.template(tplScroller);
            this.$el.append(tpl(this.model.toJSON()));
            $('#img-slider-' + this.model.id).tinycarousel();
            this._bindEvents();
        },
        _bindEvents:function () {
            var self = this;
            $(".img_scroller").click(function (event) {
                self.model.set("img", $(this).html());
            })
        },
        _clickOnImg:function (model) {
            $("#selected-img-"+this.id).html(this.get("img"));

        }

    });
});