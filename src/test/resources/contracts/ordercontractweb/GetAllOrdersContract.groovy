package contracts.ordercontractweb

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url $(consumer('v1/orders'),
                producer('v1/orders'))
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body("""
            {
              "content": [
                {
                  "id": "${value(test(anyNonEmptyString()), stub("25f8ecfa-831a-4db2-b24c-ef357474a823"))}",
                  "createdAt": "${value(test(anyNonEmptyString()), stub("2000-01-09T16: 36: 49.364+0000"))}",
                  "updatedAt": "${value(test(anyNonEmptyString()), stub("2000-02-09T16: 36: 49.364+0000"))}",
                  "status": "${value(test(anyNonEmptyString()), stub("PENDING"))}",
                  "items": [
                    {
                      "id": "${value(test(anyNonEmptyString()), stub("25f8essscfa-831a-4db2-b24c-ef357474a823"))}",
                      "createdAt": "${value(test(anyNonEmptyString()), stub("2000-01-09T16: 36: 49.364+0000"))}",
                      "updatedAt": "${value(test(anyNonEmptyString()), stub("2000-02-09T16: 36: 49.364+0000"))}",
                      "description": "${value(test(anyNonEmptyString()), stub("Monitor 4K"))}",
                      "paymentValue": "${value(test(anyNumber()), stub(2569.5))}",
                      "orderId": "${value(test(anyNonEmptyString()), stub("25f8ecfa-831a-4db2-b24c-ef357474a823"))}"
                    }
                  ]
                }
              ]
            }            
        """)
    }

}
