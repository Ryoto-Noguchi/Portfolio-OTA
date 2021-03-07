$(document).ready(function () {

  /* パスワードのマスク */
  let asters = '';
  /* 実際のパスワードの文字数分 「●」 を表示する */
  for (let i = 0; i < password.length; i++) {
    asters += '●';
  }
  $('#asters').text(asters);

  /* プロフィール画像変更ボタン押下時 */
  $('#userImg').change(function () {
    var file = $(this).prop('files')[0];
    if (!file.type.match('image.*')) {
      // クリアする
      $(this).val('');
      return;
    }
    $('#selected-status').text(`Selected photo : [${file.name}]`);
    const fd = new FormData();
    fd.append('file', $('input[type=file]')[0].files[0]); // 読み込むファイルを指定する
    $.ajax({
      url: '/portfolio/user/imgUpload',
      type: "POST",
      data: fd,
      enctype: 'multipart/form-data',
      processData: false,
      contentType: false,
      cache: false
    }).done(function (bool) {
      if (bool) {
        bootbox.alert({
          message: 'Your photo has just changed now!',
          backdrop: true,
          centerVertical: true,
          callback: function () {
            var reader = new FileReader();
            reader.onload = function () {
              $('.mypage-img').attr('src', reader.result); // FileReaderのメソッドで、ファイルの内容をStringにして取得する
            }
            reader.readAsDataURL(file); // 読み込みを実行する
            console.log('画像ファイルをimgフォルダに格納しました');
          }
        })
      }
    }).fail(function () {
      console.log('Error: ajax通信に失敗しました');
    }).always(function (result) {
      console.log("ajax通信しました");
    })
  });

  /* 修正ボタン押下時 */
  $('#modify-btn').on('click', function () {
    $('.normal').addClass('hidden');
    $('.modify').removeClass('hidden');
    $('#modify-btn').addClass('hidden');
    $('#cancel-btn').removeClass('hidden');
    $('#submit-btn').removeClass('hidden');
  });

  /*キャンセルボタン押下時*/
  $('#cancel-btn').on('click', function () {
    $('.normal').removeClass('hidden');
    $('.modify').addClass('hidden');
    $('#modify-btn').removeClass('hidden');
    $('#cancel-btn').addClass('hidden');
    $('#submit-btn').addClass('hidden');
  });

  /*修正完了ダイアログの初期化*/
  var dialog = bootbox.dialog({
    message: '<p class="text-center mb-0">Your profile picture has changed</p>',
    backdrop: true,
    centerVertical: true,
    show: false
  });

  /*ユーザ情報更新後のアラート表示*/
  console.log("completeMsg : " + completeMsg);
  if (completeMsg) {
    dialog.modal('show');
  }
});
