<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${type_toast=='success' || type_toast=='error' || type_toast=='warning' }">
        <script>
            setTimeout(() => {
                Swal.fire({
                  title: '${title_toast}',
                  text: "${message_toast}",
                  icon: '${type_toast}',
                  confirmButtonColor: '#3085d6',
                  confirmButtonText: 'Ok!'
                })
            }, 100);
        </script>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>
