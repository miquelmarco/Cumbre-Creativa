let { createApp } = Vue
createApp({
    data() {
        return {
            allEvents: [],
            events: [],
            searchInput: ""
        }
    },
    created() {
        this.getAllEvents()
    },
    mounted() {
    },
    methods: {
        getAllEvents() {
            axios.get("http://localhost:8080/api/getAllEvents")
                .then(res => {
                    this.allEvents = res.data.sort((a, b) => b.id - a.id)
                    console.log(this.events)
                }).catch(err => {
                    console.log(err)
                })
        },
        starRating(rating) {
            return '<i class="bi bi-star-fill mx-1"></i>'.repeat(rating)
        }
    },
    computed: {
        filter() {
            this.events = this.allEvents.filter(eve => eve.title.toLowerCase().includes(this.searchInput.toLowerCase()))
        }
    }
}).mount('#app')