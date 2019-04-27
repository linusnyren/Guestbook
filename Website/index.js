
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
              this.notes = result
          })
  },
  methods: {
    addNote() {
      let bodystring = '{ "author":"' +this.author +'", "message":' +this.message +'}'
      fetch('http://localhost:8080/GuestBook/GuestBook/GuestBook', {

        body: bodystring,
        headers: {
          "Content-Type": 'application/json'
        },
        method: 'POST',

      }).then(response => response.json())
      .then(result => {
          this.notes = result
      })
    }
  }
})
