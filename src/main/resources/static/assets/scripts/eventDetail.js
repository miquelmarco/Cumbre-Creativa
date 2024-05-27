let { createApp } = Vue
createApp({
    data() {
        return {
            eventObj: {},
        }
    },
    created() {
        this.getEvent()
    },
    mounted() {
    },
    methods: {
        getEvent() {
            this.queryId = new URLSearchParams(location.search).get("id")
            console.log(this.queryId)
            axios.get(`http://localhost:8080/api/getEvent/${this.queryId}`)
            .then(res => {
                this.eventObj = res.data
                console.log(this.eventObj)
            }).catch(err => {
                console.log(err)
            })
        }
    },
    computed: {
    }
}).mount('#app')