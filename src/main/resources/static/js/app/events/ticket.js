var main = {
    init : function () {
        var _this = this;
        $('#btn-ticket-save').on('click', function () {
            _this.save();
        });
    },
    save: function () {
        var data = {
            name: $('#name').val(),
            capacity: $('#capacity').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/events/ticket/new',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('행사장이 등록되었습니다.');
            window.location.href = '/events/ticket';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

main.init();