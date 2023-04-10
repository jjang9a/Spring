/**
 * 
 */
console.log("start");

var replyService = (function () {
    var sum = function (a, b) {
        return a + b;
    };

    function add(reply = {
        rno: 10,
        bno: 300,
        reply: '댓글내용',
        replyer: 'user01'
    }, callback) {
        $.ajax({
            type: 'post',
            url: '/replies/new',
            data: JSON.stringify(reply), // 자바스크립트 객체타입인 reply를 json타입으로 변환
            // -> {"rno":10, "bno":300, "reply":"댓글내용", ...}
            contentType: 'application/json',
            success: function (result) {
                if (callback) {
                    callback(result); // callback함수의 매개값으로 result를 사용
                }
            },
            error: function (reject) {
                console.error(reject);
            }
        })
    } // end of 등록

    // 글번호 -> 댓글정보 보여줌
    function getList(param = {}, callback, error) {
        // param = {bno: 300}
        // 파라메터로 넘어오는 값 중에 bno라는 값을 받아온다
        var bno = param.bno;
        var page = param.page;
        $.getJSON('/replies/pages/' + bno + '/' + page + '.json', function (data) {
            if (callback) {
                callback(data)
                // console.log(data)
            }
        }).fail(function (err) {
            if (error) {
                error(err);
                //console.log(err);
            }
        })
    }

    // 삭제
    function remove(rno, callback, error) {
        $.ajax({
            method: 'delete',
            url: '/replies/' + rno,
            success: function (result) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (reject) {
                if (error) {
                    error(reject);
                }
            }
        })
    } // end of remove

    // 수정
    function update(reply = {}, callback, error) {
        $.ajax({
            type: 'put',
            url: '/replies/' + reply.rno,
            data: JSON.stringify(reply),
            contentType: 'application/json;charset=UTF-8',
            success: function (result) {
                if (callback) {
                    callback(result)
                }
            },
            error: function (reject) {
                if (error) {
                    error(reject)
                }
            }
        })
    } // end of update

    // 단건 조회
    function get(rno, callback, error) {
        $.get('/replies/' + rno + '.json', function (result) {
            if (callback) {
                callback(result)
            }
        }).fail(function (reject) {
            if (error) {
                error(reject);
            }
        })
    }

    // 날짜 표시
    function displayTime(timeValue) {
        // 현재시간을 기준으로 24시간이 지난 데이터 -> 날짜만 보여줌
        // 24시간 이내의 데이터 -> 시간을 보여줌
        var today = new Date();
        var gap = today.getTime() - timeValue; // 1680938301000
        var dateObj = new Date(timeValue); // getFullYear, getMonth
        if (gap < (1000 * 60 * 60 * 24)) {
            var hh = dateObj.getHours();
            var mm = dateObj.getMinutes();
            var ss = dateObj.getSeconds();
            // 14:23:17
            return [(hh > 9 ? '' : '0') + hh, ':', (mm > 9 ? '' : '0') + mm, ':', (ss > 9 ? '' : '0') + ss].join('');
            // [].join : 배열 값 -> 문자열 값
        } else {
            var yy = dateObj.getFullYear();
            var mm = dateObj.getMonth() + 1;
            var dd = dateObj.getDate(); // getDay는 요일정보
            return [yy, '/', (mm > 9 ? '' : '0') + mm, '/', (dd > 9 ? '' : '0') + dd].join('');
        }
    }

    return {
        sum: sum,
        add: add,
        getList: getList,
        remove: remove,
        update: update,
        get: get,
        displayTime: displayTime
    }
})(); // 즉각실행함수

// var reply = { bno: 300, reply: 'ajax를 통한 댓글', replyer: 'user00' }
// replyService.add(reply, function(result){
//     alert("Result : " + result)
// });