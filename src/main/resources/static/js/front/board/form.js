/** 게시판 작성, 수정 양식 공통 스크립트 */

window.addEventListener("DOMContentLoaded", function() {
    try {
        CKEDITOR.replace("content", {
            height: 350,
        });
    } catch(e) {}
});

/**
* 파일 업로드 콜백 처ㅣ
*
* @param files : 업로드 완료된 파일 목록
*/
function fileUploadCallback(files) {
    console.log(files)
    if(!files || files.length == 0) return;
    const attachImages = document.getElementById("attach_images");
    const attachFiles = document.getElementById("attach_files");

    const tpl = document.getElementById("tpl_image1").innerHTML;

    const domParser = new DOMParser();
    for(const file of files) {

        let html = tpl;
        let target;
        const location = file.location;

        html = html.replace(/\[id\]/g, file.id)
                    .replace(/\[url\]/g, file.thumbsUrl[0])
                    .replace(/\[fileName\]/g, file.fileName);

        const dom = domParser.parseFromString(html, "text/html");
        const el = location == "image" || location == 'file' ? dom.querySelector(".file_images") : dom.querySelector(".file_items");

        switch(location) {
            case "image" :
                target = attachImages;
                break;
            case "file" :
                target = attachFiles;
                break;
        }

        if(target) target.appendChild(el);
    }
}

/**
* 파일 삭제 성공시 콜백 처리
* @param fileId: 파일 등록 번호
*/
function fileDeleteCallback(fileId) {
    if(!fileId) return;

    const el = document.getElementById(`file_${fileId}`);
    if(el) el.parentElement.removeChild(el);
}