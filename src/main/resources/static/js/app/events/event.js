var main = {
    init: function () {
        var _this = this;
        $('#btn-event-save').on('click', function () {
            _this.save();
        });

        $('#btn-event-update').on('click', function () {
            _this.update();
        });

        $('#btn-event-delete').on('click', function () {
            _this.delete();
        });
    },

    save: function () {
        var data = {
            hallId: $('#hallId').val(),
            name: $('#name').val(),
            detail: $('#detail').val(),
            price: $('#price').val(),
            recruitAmount: $('#recruitAmount').val(),
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val(),
            recruitStartDate: $('#recruitStartDate').val(),
            recruitEndDate: $('#recruitEndDate').val(),
            field: $('#field').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/events/event/new',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('행사가 등록되었습니다.');
            window.location.href = '/events/event';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update : function () {
        var data = {
            name: $('#name').val(),
            hallId: $('#hallId').val(),
            field: $('#field').val(),
            detail: $('#detail').val(),
            price: $('#price').val(),
            recruitAmount: $('#recruitAmount').val(),
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val(),
            recruitStartDate: $('#recruitStartDate').val(),
            recruitEndDate: $('#recruitEndDate').val(),
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/events/event/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('행사 정보가 수정되었습니다.');
            window.location.href = '/events/event/list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/events/event/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('행사 정보가 삭제되었습니다.');
            window.location.href = '/events/event/list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();