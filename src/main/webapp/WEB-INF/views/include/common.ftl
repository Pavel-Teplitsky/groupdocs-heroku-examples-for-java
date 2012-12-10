<#import "/spring.ftl" as fmspring                                            />

<#macro navBar menuItems activeItem brandTitle brandUrl>
	<#assign keys = menuItems?keys>
	<div class="navbar navbar-inverse navbar-fixed-top">
	    <div class="navbar-inner">
	        <div class="container">
	            <a class="brand" href="${brandUrl}">${brandTitle}</a>
	            <div class="nav-collapse collapse">
	                <ul class="nav">
	                	<#list keys as key>
		                    <li<#if key==activeItem > class="active"</#if>><a href="${menuItems[key]}">${key}</a></li>
	                    </#list>
	                </ul>
	            </div>
	        </div>
	    </div>
	</div>
</#macro>
