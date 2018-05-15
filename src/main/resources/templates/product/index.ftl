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
								<form role="form"  method="post" action="/sell/product/save">
									<div class="form-group">
										 <label>名称</label>
										 <input type="text" name="productName" class="form-control" value="${(productInfo.productName)!''}" />
									</div>
									<div class="form-group">
										 <label>价格</label>
										 <input type="text" name="productPrice" class="form-control" value="${(productInfo.productPrice)!''}" />
									</div>
									<div class="form-group">
										 <label>库存</label>
										 <input type="number" name="productStock" class="form-control" value="${(productInfo.productStock)!''}" />
									</div>
									<div class="form-group">
										 <label>描述</label>
										 <input type="text" name="productDescription" class="form-control" value="${(productInfo.productDescription)!''}" />
									</div>
									<div class="form-group">
										 <label>图片</label>
										 <img  height="100" width="100" src="${(productInfo.productIcon)!''}" alt="">
										 <input type="text" name="productIcon" class="form-control" value="${(productInfo.productIcon)!''}" />
									</div>
									<div class="form-group">
										 <label>类目</label>
										 <select name="categoryType" class="form-control">
										   <#list categoryList as category>
										       <option value="${category.categoryType}"
										               <#if (productInfo.categoryType)?? && productInfo.categoryType ==category.categoryType>
										                    selected        
										                </#if>
										               
										          >${category.categoryName}
										       </option>
										   </#list>
										 </select>
										
									</div>
								 <input hidden type="text" name="productId" value="${(productInfo.productId)!''}"> <!--把productId传过去-->
								 <button type="submit" class="btn btn-default">提交</button>
								</form>
							</div>
					 </div>
					                   
         
              </div>
          </div>
     
     
     </div>
	

</body>



</html>