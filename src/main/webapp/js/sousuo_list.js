$(function () {
    //获得传来的模糊查询的word
    var word = $.cookie("search_word");
    /***
     * 模糊查询，将结果展示到主页中
     */
    $.post("/LOLdaojucheng/product/mohuchaxun",{"word":word},function (result) {
        $("#sousuo_list_div_id_02").html("");
        $(result.data).each(function () {
            $("#sousuo_list_div_id_02").append("<div class='details_div'><a ><img src='/LOLdaojucheng/"+this.mainImage+"' class='productMainImage'></a>" +
                "                               <a><p class='productName'>"+this.name+this.subtitle+"</p></a>" +
                "                               <p class='price_Q'>Q币价： <span class='price_Qb'>"+this.price+"Q币</span></p>" +
                "                               <p class='price_W'>微信价：<span class='price_Wx'>"+(this.price-5)+"元</span></p>" +
                "                               <button class='buy_button'>立即购买</button>" +
                "                               </div>")
        });
    });



    /***
     * 搜索热门商品并展览出来
     */
    $.post("/LOLdaojucheng/product/selectHot",function (result) {
        $(result.data).each(function () {
            $("#sousuo_list_div_id_01").append("<a class='hotDetails' value='"+this.id+"'  style='width: 100%'>" +
                "                <div style='width: 100%'>" +
                "               <img class='list_img_class_01' src='/LOLdaojucheng/"+this.subImages+"'>" +
                "                <div class='list_div_class_01'><span>"+this.name+this.subtitle+"<br/>Q币价："+this.price+"<br/>微信价："+(this.price-5)+"</span></div>" +
                "                </div>" +
                "                </a>");
        },"json");

    });
});