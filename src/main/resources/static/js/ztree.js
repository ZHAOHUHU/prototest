var setting = {
    view: {
        selectedMulti: false
    },
    edit: {
        enable: true,
        showRemoveBtn: false,
        showRenameBtn: false
    },
    data: {
        keep: {
            parent: false,
            leaf: false
        },
        simpleData: {
            enable: true,
            idKey: "id",
			pIdKey: "pId",
			rootPId: 1
        }
    },
    /*async: {
        enable: true,   //设置启用异步加载
        type: "get",   //异步加载类型:post和get
        contentType: "application/json", //定义ajax提交参数的参数类型，一般为json格式
        url: "/Design/Get",  //定义数据请求路径
        autoParam: ["id=id", "name=name"] //定义提交时参数的名称，=号前面标识节点属性，后面标识提交时json数据中参数的名称
    },*/

};

var zNodes; 
var log, className = "dark";

function beforeDrag(treeId, treeNodes) {
    return false;
}

//=======================================编辑
function edit() {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getSelectedNodes(),
        treeNode = nodes[0];

    if (nodes.length == 0) {
        alert("请选中节点");
        return;
    }
    $("#dialog").show();
    $("#dialog").dialog({
        title: '修改',
        width: 500,
        height: 300,
        modal: true,
        buttons: [{
            text: '保存',
            id: 'Edit',
            iconCls: 'icon-edit',
            handler: function () {
                $('#Edit').linkbutton('disable');
                $.ajax({
                    type: "GET",
                    url: "/href",
                    async: true,
                    data: $.param({'id': (100 + newCount), 'pid': treeNode.id}) + '&' + $('#myFrom').serialize(),
                    success: function (data) {

                        $.messager.alert('提示', '新增成功', 'info');
                        $("#dialog").dialog("close");
                        $('#menuGrid').datagrid('reload');
                        $('#Edit').linkbutton('enable');
                    }
                });
            }
        }, {
            text: '取消',
            iconCls: 'icon-help',
            handler: function () {
                $("#dialog").dialog("close");

            }
        }]
    });
}


//添加子节点==================================
var newCount = 1;
function add(e) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getSelectedNodes(),
        treeNode = nodes[0];
    $("#adddialog").show();
    $("#adddialog").dialog({
        title: '修改',
        width: 500,
        height: 300,
        modal: true,
        buttons: [{
            text: '保存',
            id: 'Edit',
            iconCls: 'icon-edit',
            handler: function () {
                $('#Edit').linkbutton('disable');
                $.ajax({
                    type: "GET",
                    url: "/href",
                    async: true,
                    data: $.param({'id': treeNode.id, 'pid': treeNode.pid}) + '&' + $('#addFrom').serialize(),
                    success: function (data) {

                        $.messager.alert('提示', '新增成功', 'info');
                        $("#adddialog").dialog("close")
                        //树的id是treeDemo，新增成功后完成对tree的刷新
                        $('#treeDemo').datagrid('reload');
                        $('#Edit').linkbutton('enable');
                    }
                });
            }
        }, {
            text: '取消',
            iconCls: 'icon-help',
            handler: function () {
                $("#adddialog").dialog("close");

            }
        }]
    });
    //前端添加子节点的方法
    // treeNode = zTree.addNodes(treeNode, {
    //     id: (100 + newCount),
    //     pId: treeNode.id,
    //     name: "new node" + (newCount++)
    // });
};
//添加根节点
function addParent(){
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getSelectedNodes(),
        treeNode = nodes[0];
    if (nodes.length == 0) {
        alert("请至少选中一个节点");
        return;
    }
    if(treeNode.level==0){
        $("#adddialog").show();
        $("#adddialog").dialog({
            title: '修改',
            width: 500,
            height: 300,
            modal: true,
            buttons: [{
                text: '保存',
                id: 'Edit',
                iconCls: 'icon-edit',
                handler: function () {
                    $('#Edit').linkbutton('disable');
                    $.ajax({
                        type: "GET",
                        url: "/href",
                        async: true,
                        data: $.param({'id': (100 + newCount), 'pid': 0}) + '&' + $('#addFrom').serialize(),
                        success: function (data) {

                            $.messager.alert('提示', '新增成功', 'info');
                            $("#adddialog").dialog("close");
                            //树的id是treeDemo，新增成功后完成对tree的刷新
                            $('#treeDemo').datagrid('reload');
                            $('#Edit').linkbutton('enable');
                        }
                    });
                }
            }, {
                text: '取消',
                iconCls: 'icon-help',
                handler: function () {
                    $("#adddialog").dialog("close");

                }
            }]
        });
        // treeNode = zTree.addNodes(null, {
        //     id: (100 + newCount),
        //     pId: 0,
        //     name: "new node" + (newCount++)
        // });
    }else{
        alert("您选中的不是根节点，请重新选择");
    }
}


//移除
function remove(e) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getSelectedNodes(),
        treeNode = nodes[0];
    if (nodes.length == 0) {
        alert("Please select one node at first...");
        return;
    }
    if (treeNode.isParent) {
        alert("不能删除");
        return;
    }
    alert("确认删除" + treeNode.name + "节点");
    var callbackFlag = $("#callbackTrigger").attr("checked");
    $.ajax({
        type:"POST",
        dataType:"json",
        url:"/deleteById.html",
        data:{id:treeNode.id,pId:treeNode.pId},
        success:function(data){
            if(data.success){
            	//zTree.removeNode(treeNode, callbackFlag);
                alert("删除成功");
            }else{
                alert("删除失败");
            }
        }
    });
};

function clearChildren(e) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getSelectedNodes(),
        treeNode = nodes[0];
    if (nodes.length == 0 || !nodes[0].isParent) {
        alert("Please select one parent node at first...");
        return;
    }
    zTree.removeChildNodes(treeNode);
};

function initNodes(){
	$.ajax({
		async:false,
		cache:false,
		type:"post",
		dataType:"json",
	    url:"/showtree.html",
	    error:function(){
	    	alert('请求失败');
	    },
	    success:function(data){
	    	zNodes = data.tree;
	    }
	});
};

$(document).ready(function () {
	initNodes();
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    //设置一个添加根节点的
    $("#addParent").bind("click",  addParent);
    $("#addLeaf").bind("click",  add);
    $("#edit").bind("click", edit);
    $("#remove").bind("click", remove);
    $("#clearChildren").bind("click", clearChildren);
});