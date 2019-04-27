
new Vue({
  data: {
    author: null,
    message: null,
    notes: null
  },
  el: '#app',
  created() {
      fetch('http://localhost:8080/GuestBook/GuestBook/GuestBook')
          .then(response => response.json())
          .then(result => {
              let list = []
              for (var i = 0; i < result.length; i++) {
              list.push({
                "message": result[i].message,
                "id": result[i].id,
                "author": result[i].author,
                "date": (new Date(parseInt(result[i].date))).toString().substring(0,15)
              })
            }
            this.notes = list;
          })
  },
  methods: {
    addNote() {
      let bodystring = '{ "author": "' +this.author +'", "message": "' +this.message +'"}'
      console.log(bodystring)
      fetch('http://localhost:8080/GuestBook/GuestBook/GuestBook', {

        body: bodystring,
        headers: {
          "Content-Type": 'application/json'
        },
        method: 'POST',

      }).then(response => response.json())
      .then(result => {
          created()
      })
    }
  }
})
