<%--
  Created by IntelliJ IDEA.
  User: XT
  Date: 2020/3/8
  Time: 0:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <%--大于首页，才显示--%>
    <c:if test="${page.pageNo > 1}">
        <a href="${page.url}&pageNo=1">首页</a>
        <a href="${page.url}&pageNo=${page.pageNo-1}">上一页</a>
    </c:if>
    <%-- 显示连续的5页 --%>
    <c:choose>
        <%--情况1：如果总页码小于等于5 的情况，页码的范围是：1-总页码--%>
        <c:when test="${page.pageTotal <= 5}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${page.pageTotal}"></c:set>
        </c:when>
        <%--情况2：总页码大于5 的情况--%>
        <c:when test="${page.pageTotal > 5}">
            <c:choose>
                <%--小情况1：当前页码为前面3 个：1，2，3 的情况，页码范围是：1-5.--%>
                <c:when test="${page.pageNo <= 3}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                </c:when>
                <%--小情况2：当前页码为最后3 个，8，9，10，页码范围是：总页码减4 - 总页码--%>
                <c:when test="${page.pageNo >= page.pageTotal - 2}">
                    <c:set var="begin" value="${page.pageTotal-4}"></c:set>
                    <c:set var="end" value="${page.pageTotal}"></c:set>
                </c:when>
                <%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
                <c:otherwise>
                    <c:set var="begin" value="${page.pageTotal-2}"></c:set>
                    <c:set var="end" value="${page.pageTotal+2}"></c:set>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <c:forEach begin="${begin}" end="${end}" step="1" var="i">
        <c:if test="${page.pageNo == i}">
            【${i}】
        </c:if>
        <c:if test="${page.pageNo != i}">
            <a href="${page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <%-- 如果已经是最后一页，则不显示下一页，末页--%>
    <c:if test="${page.pageNo < page.pageTotal}">
        <a href="${page.url}&pageNo=${page.pageNo+1}">下一页</a>
        <a href="${page.url}&pageNo=${page.pageTotal}">末页</a>
    </c:if>
    共${page.pageTotal}页，${page.pageTotalCount}条记录 到第<input value="4" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
        <script>
            $(function () {
                // 跳转指定的页码
                $("#searchPageBtn").click(function () {
                    var pageNo = $("#pn_input").val();
                    // javaScript 语言中提供了一个location 地址栏对象
                    // 它有一个属性叫href.它可以获取浏览器地址栏中的地址
                    // href 属性可读，可写
                    location.href = "${pageScope.basePath}${page.url}&pageNo=" + pageNo;
                })
            })
        </script>
</div>
