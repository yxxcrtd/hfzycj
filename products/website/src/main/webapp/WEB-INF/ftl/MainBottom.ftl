			</div>
			<#--<div id="mainRight"></div>-->
		</div>
		<input type="hidden" id="contextPath" value="${request.contextPath}" />
		<input type="hidden" id="active" value="${active}" />
		<script language="javascript" src="${request.contextPath}/js/manage.min.js"></script>
		<#if ("Item" == active || "vote" == active || "log" == active || "user" == active || "logo" == active || "park" == active || "information" == active || "feedback" == active || "ad" == active || "link" == active || "manager" == active || "video" == active || "news" == active || "game" == active || "activity" == active || "match" == active|| "chess" == active|| "tutorial" == active|| "picture" == active|| "menu" == active|| "communal" == active)>
			<script language="javascript" src="${request.contextPath}/js/${active}.min.js"></script>
		</#if>
	</body>
</html>