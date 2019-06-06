function aa(maxSize, maxWidth, maxHeight) {
    var returnString = limitImg('img', maxSize, false, maxWidth, maxHeight);
    if (returnString == "") {
        alert("图片上传成功");
    } else {
        alert(returnString);
        return false;
    }
}

function limitImg() {
    var img = document.getElementById(arguments[0]);//显示图片的对象
    var maxSize = arguments[1];
    var allowGIF = arguments[2] || false;
    var maxWidth = arguments[3] || 0;
    var maxHeight = arguments[4] || 0;
    var postfix = getPostfix(img.src);
    var str = ".jpg";
    if (allowGIF) {
        str += ".gif"
    }
    if (str.indexOf(postfix.toLowerCase()) == -1) {
        if (allowGIF) {
            return "图片格式不对，只能上传jpg或gif图像";
        } else {
            return "图片格式不对，只能上传jpg图像";
        }
    } else if (img.fileSize > maxSize * 1024) {
        return "图片大小超过限制,请限制在" + maxSize + "K以内";
    } else {
        if (img.fileSize == -1) {
            return "图片格式错误，可能是已经损坏或者更改扩展名导致，请重新选择一张图片";
        } else {
            if (maxWidth > 0) {
                if (img.width > maxWidth) {
                    return "图片宽度超过限制，请保持在" + maxWidth + "像素内";
                } else {
                    if (img.height > maxHeight) {
                        return "图片高度超过限制，请保持在" + maxHeight + "像素内";
                    } else {
                        return "";
                    }
                }
            } else {
                return "";
            }
        }
    }
}
//根据路径获取文件扩展名
function getPostfix(path) {
    return path.substring(path.lastIndexOf("."), path.length);
}