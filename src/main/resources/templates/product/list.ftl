<html>

    <#include "../common/head.ftl">
    <!--商品列表 -->
<body>

    <div id="wrapper" class="toggled">
          <!--边栏 sidebar  -->
           <#include  "../common/nav.ftl">
           
           
           
           
          <!--主要内容区域  -->
          <div id="page-content-wrapper"> 
            	<div class="container-fluid">
					<div class="row clearfix">
						<div class="col-md-12 column">
							<table class="table table-bordered table-condensed" >
								<thead>
									<tr>
										<th>商品id</th>
										<th>名称</th>
										<th>图片</th>
										<th>单价</th>
										<th>库存</th>
										<th>描述</th>
										<th>类目</th>
										<th>创建时间</th>
										<th>修改时间</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								<tbody>
					   				 <#list productInfoPage.content as productDTO>
											<tr>
												<td>${productDTO.productId}</td>
												<td>${productDTO.productName}</td>
												<td><img height="100"  width="100" src="${productDTO.productIcon}"/></td>
												<td>${productDTO.productStock}</td>
												<td>${productDTO.productDescription}</td>
												<td>${productDTO.categoryType}</td>
					                            <td>${productDTO.createTime}</td>
												<td>${productDTO.updateTime}</td>
												<td><a href="/sell/product/index?productId=${productDTO.productId}">修改</a></td>
												<td>
												   <#if  productDTO.getProductStatusEnum().message =="在架"> 
												       <a href="/sell/product/off_sale?productId=${productDTO.productId}">下架</a>  
  	 	  										    <#else>
			                                           <a href="/sell/product/on_sale?productId=${productDTO.productId}">上架</a>  									         
												    </#if> 
												 
												</td>
											</tr>
										   </#list>
								</tbody>
							</table>
						</div>
			
						<div class="col-md-12 column">
						<ul class="pagination pull-right">
						   <#if currentPage lte 1>
							<li class="disabled"><a href="#">上一页</a></li>
						   <#else>
							<li><a href="/sell/product/list?page=${currentPage-1}&size=${size}">上一页</a></li>
						   </#if>	
							
							<#list 1..productInfoPage.getTotalPages() as index>
							  <#if currentPage == index>
							  	 <li class="disabled"><a href="#">${index}</a></li>
							   <#else> 
							  	 <li ><a href="/sell/product/list?page=${index}&size=${size}">${index}</a></li>
							   </#if>
							</#list>
							<#if currentPage gte productInfoPage.getTotalPages()>
						      <li class="disabled"><a href="#">下一页</a></li>
						    <#else>
							  <li><a href="/sell/product/list?page=${currentPage+1}&size=${size}">下一页</a></li>
							</#if>
						</ul>
					</div>
			 	</div>
                   
         
              </div>
          </div>
     </div>
	

</body>



</html>