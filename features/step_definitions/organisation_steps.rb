Given /^the following organisations:$/ do |organisations|
  organisations.hashes.each do |organisation|
    puts organisation
    organisation.each_pair {|k, v| v.split( ).each {|name| Organisation.make(k => name) }}
  end
end

Given /^the following (active|inactive) organisation names: "(.*)"$/ do |active, organisations|
  organisations.split( ).each do |organisation|
    Organisation.make(:name => organisation, :active => (active=="inactive")? false : true)
  end
end

When /^I delete the (\d+)(?:st|nd|rd|th) organisation$/ do |pos|
  visit organisations_url
  within("table tr:nth-child(#{pos.to_i+1})") do
    click_link "Destroy"
  end
end

Then /^I should see the following organisations:$/ do |expected_organisations_table|
  expected_organisations_table.diff!(tableish('table tr', 'td,th'))
end

Given /^organisation "([^\"]*)" has been tagged (\d*) times$/ do |organisation, nbtimes|
  org = Organisation.make(:name => organisation)
  nbtimes.to_i.times{org.}
end