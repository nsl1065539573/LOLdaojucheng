<%--
  Created by IntelliJ IDEA.
  User: nansongling
  Date: 2019/3/2
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <div id="daohanglan_div_id_01" class="row">

            <button class="shubiao" id="daohanglan_button_id_01">全部分类</button>

        <div id="daohanglan_div_id_02">
            <ul id="daohanglan_ul_id_01">
            </ul>
        </div>
    </div>
<script type="text/javascript">
    $(function () {


        $.post("/LOLdaojucheng/category/selectAll",
            function (result) {
                $(result.data).each(function () {
                        $("#daohanglan_ul_id_01").append("<a class='a3' ><li style='display: inline-block ' value="+this.id+" class='l3'>"+this.name+"</li></a>");

            });
        },"json");
        $("#daohanglan_ul_id_01").on("click",".l3",function () {
            var value = $(this).attr("value");
            $.cookie("daohanglanID",value);
            window.location.href="/LOLdaojucheng/protalView/product_list";

        });
        $("#daohanglan_button_id_01").click(function () {
            $.cookie("daohanglanID",0);
            window.location.href="/LOLdaojucheng/protalView/product_list";
        });
    })
</script>