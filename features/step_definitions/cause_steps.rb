Given /^the following causes:$/ do |causes|
  Cause.create!(causes.hashes)
end

When /^I delete the (\d+)(?:st|nd|rd|th) cause$/ do |pos|
  visit causes_url
  within("table tr:nth-child(#{pos.to_i+1})") do
    click_link "Destroy"
  end
end

Then /^I should see the following causes:$/ do |expected_causes_table|
  expected_causes_table.diff!(tableish('table tr', 'td,th'))
end
