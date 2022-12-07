var main = {
    init : function () {
        var _this = this;
        $('#btn-hall-save').on('click', function () {
            _this.save();
        }),

        $('#btn-hall-update').on('click', function () {
            _this.update();
        });

        $('#btn-hall-delete').on('click', function () {
            _this.delete();
        });
    },
    save: function () {
        var data = {
            name: $('#name').val(),
            capacity: $('#capacity').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/events/hall/new',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('행사장 정보가 등록되었습니다.');
            window.location.href = '/events/hall';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update : function () {
        var data = {
            name: $('#name').val(),
            capacity: $('#capacity').val(),
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/events/hall/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('행사장 정보가 수정되었습니다.');
            window.location.href = '/events/hall/list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/events/hall/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('행사장 정보가 삭제되었습니다.');
            window.location.href = '/events/hall/list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

main.init();