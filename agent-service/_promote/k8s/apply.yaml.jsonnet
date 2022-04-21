local a = 1;
local deploy = import "kube-deployment.libsonnet";

{
    local name = "adeola ojo",
    local email = "ojoadeolagabriel@gmail.com",
    local address_parts = {
        line_1: "94 lordship park",
        line_2: "flat 2",
    },

    PayLoad: {
    local data = self,
    local ip_address = "192.16.12.1",
    local add_decade(x, displacement = 10) =
        local result = x + 10;
        result,

    local build_fruit_counter (apple_count = 1, orange_count = 2, strawberry_count = 3) =
        local result = [
            { name: "apple", count: apple_count},
            { name: "orange", count: orange_count},
            { name: "strawberry", count: strawberry_count},
        ];
        result,

        user_name: name,
        email_address: email,
        full_address: address_parts,
        age: 5,
        age_self: $.PayLoad.age,
        slugs: ["tall", "smart", "love"],
        age_parts: {
          month: 1,
          year: 2022
        },
        age_tomorrow: {
           next_month: $['PayLoad'].age_parts.month + 1,
           next_decade: add_decade(x = $['PayLoad'].age_parts.year),
        },
        age_yesterday: data.slugs,
        age_array: [1,2,3] + [4],
        age_array_comparison: self.age_array == [1,2,3] + [4],
        ip_parts: {
            network_id: std.split(ip_address, '.')[0]  + '.' + std.split(ip_address, '.')[1],
            host_id: std.split(ip_address, '.')[2] + '.' + std.split(ip_address, '.')[3]
        } + if self.age >= 6 then {host_age: 6} else {host_age: 0, error_message: "could not parse"},
        fruit_store: build_fruit_counter(orange_count = 10)
    }
}